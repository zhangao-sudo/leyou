package com.leyou.cart.Service;

import com.item.leyou.pojo.Sku;
import com.leyou.cart.Client.GoodsClient;
import com.leyou.cart.interceptor.CartInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhang Ao on 2020/1/30 17:43
 * Email:17863572518@163.com
 */
@Service
public class CartService {
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX="user:cart:";
    public void addcart(Cart cart) {

        //拦截器获取用户信息
        UserInfo userInfo = CartInterceptor.getUserInfo();
        //获取用户的购物车信息  Map<  skuid ,cart>
        BoundHashOperations<String, Object, Object> BoundHashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId().toString());

         String key=cart.getSkuId().toString();
         Integer num=cart.getNum();
        //判断购物车是否有相同的商品
        if(BoundHashOperations.hasKey(key))
        {
            //有的话num进行相加
            String cartJson = BoundHashOperations.get(key).toString();
             cart= JsonUtils.parse(cartJson,Cart.class);
            cart.setNum(cart.getNum()+num);
            BoundHashOperations.put(key,JsonUtils.serialize(cart));


        }else{
            Sku sku = this.goodsClient.findSkubyId(cart.getSkuId());
            cart.setUserId(userInfo.getId());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            BoundHashOperations.put(key,JsonUtils.serialize(cart));
        }




        //没有的添加进入购物车




    }
    public List<Cart> queryCartList() {
        // 获取登录用户
        UserInfo user = CartInterceptor.getUserInfo();

        // 判断是否存在购物车
        String key = KEY_PREFIX + user.getId();
        if(!this.redisTemplate.hasKey(key)){
            // 不存在，直接返回
            return null;
        }
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        List<Object> carts = hashOps.values();
        // 判断是否有数据
        if(CollectionUtils.isEmpty(carts)){
            return null;
        }
        // 查询购物车数据
        return carts.stream().map(o -> JsonUtils.parse(o.toString(), Cart.class)).collect(Collectors.toList());
    }
    public void updateCarts(Cart cart) {
        // 获取登陆信息
        UserInfo userInfo = CartInterceptor.getUserInfo();
        String key = KEY_PREFIX + userInfo.getId();
        // 获取hash操作对象
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(key);
        // 获取购物车信息
        String cartJson = hashOperations.get(cart.getSkuId().toString()).toString();
        Cart cart1 = JsonUtils.parse(cartJson, Cart.class);
        // 更新数量
        cart1.setNum(cart.getNum());
        // 写入购物车
        hashOperations.put(cart.getSkuId().toString(), JsonUtils.serialize(cart1));
    }

    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo user = CartInterceptor.getUserInfo();
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }
}
