package net.brightroom.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface FeatureManagementMapper {
  Boolean check(@Param("featureName") String featureName);
}
