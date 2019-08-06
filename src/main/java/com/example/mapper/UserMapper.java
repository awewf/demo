package com.example.mapper;
 
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
 

@Mapper
public interface UserMapper {
 
   public User Sel(int id);

   public void insertData(User user);
}
