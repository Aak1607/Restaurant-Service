package com.restaurantsearchservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.restaurantsearchservice.model.FoodDetails;
import com.restaurantsearchservice.model.RestaurantModel;
import com.restaurantsearchservice.repository.FoodDetailsSearchRepository;
import com.restaurantsearchservice.repository.RestaurantSearchRepository;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private RestaurantSearchRepository restaurantSearchRepository;
	@Autowired
	private FoodDetailsSearchRepository foodDetailsSearchRepository;
	
	@Override
	public Page<RestaurantModel> findByAreaRatingBudgetDAO(String area, float rating, float minimumOrderPrice,
			Pageable page) {
		Page<RestaurantModel> modelObject = restaurantSearchRepository.findByAreaRatingBudget(area, rating, minimumOrderPrice, page);
		return modelObject;
	}

	@Override
	public Page<RestaurantModel> findByAreaAndCuisineDAO(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page) {
		
		return restaurantSearchRepository.findByAreaAndCuisine(area, cuisineType, rating, minimumOrderPrice, page);
	}

	@Override
	public Page<RestaurantModel> findByAreaAndNameDAO(String area, String restaurantName, Pageable page) {
		
		return restaurantSearchRepository.findByAreaAndName(area, restaurantName, page);
	}

	@Override
	public RestaurantModel findByIdDAO(String id) {
		Optional<RestaurantModel> resData=restaurantSearchRepository.findById(id);
		if(resData.isPresent()) {
			return resData.get();
		}
		return null;
		
	}

	@Override
	public Page<RestaurantModel> findByLonAndLatDAO(float rating, float minimumOrderPrice, float distance, double latitude, double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonAndLat(rating, minimumOrderPrice, distance, latitude, longitude, page);
	}

	@Override
	public Page<RestaurantModel> findByLonLatAndNameDAO(String restaurantName, float distance, double latitude,
			double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonLatAndName(restaurantName, distance, latitude, longitude, page);
	}

	@Override
	public Page<RestaurantModel> findByLonLatRatingBudgetDAO(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonLatRatingBudget(cuisineType, rating, minimumOrderPrice, distance, latitude, longitude, page);
	}

	@Override
	public List<FoodDetails> getFoodDetailsByRestaurantIdDAO(String resId) {
		
		return foodDetailsSearchRepository.getFoodDetailsByRestaurantId(resId);
	}

	@Override
	public FoodDetails getFoodDetailsByRestaurantIdAndFoodIdDAO(String resId, String foodId) {
		
		return foodDetailsSearchRepository.getFoodDetailsByRestaurantIdAndFoodId(resId, foodId);
	}
	
	@Override
	public RestaurantModel updateRestaurantDetails(RestaurantModel model) {
		return restaurantSearchRepository.save(model);
	}
	
}
