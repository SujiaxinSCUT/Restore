package com;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.pojo.Block;
import com.pojo.RWSet;
import com.pojo.ReadSet;
import com.pojo.responsePOJO.LineChartDataResponse;
import com.pojo.responsePOJO.PageableResponseBlock;
import com.pojo.responsePOJO.PageableResponseRWSet;
import com.requestAPI.UserAPI;
import com.requestAPI.retrofitBuilder.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SimpleTest {

	private static String authorization = "Bearer da5f1b21-12ed-4fd3-b81a-9529f2811839";
	
    private static Retrofit retrofit;
    private static UserAPI userAPI;

    static {
        try {
            retrofit = RetrofitBuilder.getRetrofit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userAPI = retrofit.create(UserAPI.class);
    }
	@Test
	public void getBlocks() throws IOException {
//		Call<PageableResponseBlock> blockPage = userAPI.getDescBlockPageable(authorization,0,5);
//		Response<PageableResponseBlock> response = blockPage.execute();
//		
//		Set<Block> blockSet = response.body().getContents();
//		
//		for(Block b:blockSet) {
//			System.out.println(b.getTxCount());
//		}
//		Call<LineChartDataResponse<Date>> dataPage = userAPI.getRWSetsLastFiveDays(authorization);
//		Response<LineChartDataResponse<Date>> response = dataPage.execute();
//		
//		Map<Date,Integer> maps = response.body().getContents();
//		
//		for(Date key:maps.keySet()) {
//			System.out.println("key:"+key+" value:"+maps.get(key));
//		}
//		Call<PageableResponseRWSet> blockPage = userAPI.getDescRWSetsPageable(authorization,0,5);
//		Response<PageableResponseRWSet> response = blockPage.execute();
//		
//		Set<RWSet> blockSet = response.body().getContents();
		

	}
}
