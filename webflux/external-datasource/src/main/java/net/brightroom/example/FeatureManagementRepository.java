package net.brightroom.example;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface FeatureManagementRepository extends ReactiveCrudRepository<FeatureManagement, String> {}
