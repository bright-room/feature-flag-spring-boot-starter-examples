package net.brightroom.example;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeatureManagementMapper {

  Boolean findEnabledByFeatureName(String featureName);
}
