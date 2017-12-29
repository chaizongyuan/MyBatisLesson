package cn.easytop.lesson02.annotion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import cn.easytop.lesson02.Food;

public interface FoodMapper {
	/**
	 * 根据名称 查询所有的菜品信息
	 * @param foodName
	 * @return
	 */
	@Select("select * from food where foodname =#{foodName} and price=#{price}")
	public List<Map> queryFood(@Param("foodName") String foodName,@Param("price") String price);
	
	
	@Select("select * from food where foodname like '%${foodName}%'")
	public List<Food> queryFoodByFoodName(@Param("foodName") String foodName);
	
	/**
	 * 根据id删除菜品
	 * @param foodId
	 */
	@Delete("delete from food where foodid=#{0}")
	public void deleteFood(String foodId);
	
	/**
	 * 插入food
	 * @param food
	 */
	@SelectKey(before=true,keyProperty="foodId",resultType=int.class,statement="select FOOD_SEc.Nextval from dual")
	@Insert("insert into food values(#{foodId},#{foodName},#{price})")
	public void saveFood(Food food);

	
}
