import com.item.leyou.bo.SpuBo;
import com.item.leyou.pojo.Spu;
import com.leyou.common.PageResult;
import com.leyou.elasticsearch.Elasticserach;
import com.leyou.elasticsearch.client.CategoryClient;

import com.leyou.elasticsearch.client.GoodsClient;
import com.leyou.elasticsearch.poji.Goods;
import com.leyou.elasticsearch.resposity.ItemResposity;
import com.leyou.elasticsearch.service.SearchService;
import org.elasticsearch.search.aggregations.metrics.geobounds.InternalGeoBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhang Ao on 2020/1/23 19:37
 * Email:17863572518@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Elasticserach.class)
public class IndexTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemResposity itemResposity;
    @Autowired
    private SearchService searchService;
    @Autowired
    private GoodsClient goodsClient;

    @Test
    public void Test() {
        this.elasticsearchTemplate.createIndex(Goods.class);
        this.elasticsearchTemplate.putMapping(Goods.class);
        Integer page=1;
        Integer rows=100;
        do {
            // 分批查询spuBo
            PageResult<SpuBo> pageResult = this.goodsClient.querySpuBoByPage(null, true, page, rows);
            // 遍历spubo集合转化为List<Goods>
            List<Goods> goodsList = pageResult.getItems().stream().map(spuBo -> {
                try {
                    return this.searchService.buildGoods((Spu) spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            this.itemResposity.saveAll(goodsList);

            // 获取当前页的数据条数，如果是最后一页，没有100条
            rows = pageResult.getItems().size();
            // 每次循环页码加1
            page++;
        } while (rows == 100);
    }

    }



