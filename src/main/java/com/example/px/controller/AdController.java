package com.example.px.controller;

import com.example.px.dto.AdCreateRequest;
import com.example.px.dto.AdResponse;
import com.example.px.service.AdService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //类级别的注解:类是一个控制器
@RequestMapping("/api/ads") //类级别的注解:根路径
public class AdController { //定义了一个公共的类

	private final AdService adService; //声明私有、不可变的成员变量

	public AdController(AdService adService) { //构造器注入
		this.adService = adService; //将传入的参数赋值给类的成员变量
	}

	@GetMapping //方法级别的注解,因为没有指定具体路径,它会继承类上的/api/ads
	public List<AdResponse> listAds() { //这个方法对外暴露，返回一个AdResponse对象的列表
		return adService.listAds(); //调用service的listAds方法名
	}

	@GetMapping("/{id}") //方法级别的注解,GET请求，路径为/api/ads/{id}
	public AdResponse getAd(@PathVariable Long id) {  //@PathVariable Long id：这个注解会把URL路径中的{id}部分提取出来，自动转换成Long类型，并赋值给参数id。
		return adService.getAd(id); //Service层的getAd(id)方法，获取对应的广告详情并返回
	}

	@PostMapping("/add") //POST请求，路径为/api/ads/add
	public ResponseEntity<AdResponse> createAd(@RequestBody AdCreateRequest request) {
		//ResponseEntity<AdResponse>：这是一种特殊的返回值，允许我们精确控制HTTP状态码、响应头和响应体。这里我们把状态码设为201 Created，比默认的200 OK更符合RESTful规范
		//@RequestBody AdCreateRequest request：这个注解告诉Spring，HTTP请求体里的JSON数据应该被反序列化成AdCreateRequest这个Java对象。

		return ResponseEntity.status(HttpStatus.CREATED).body(adService.createAd(request)); // 调用Service层，传入创建请求，Service会执行校验、保存到数据库等操作，最后返回一个完整的AdResponse对象（包含自动生成的id和createdAt等）
	}
}
