package com.example.mygarden.key_feature.domain.usecase

import com.example.mygarden.key_feature.domain.KeyFeatureRepository
import com.example.mygarden.key_feature.domain.model.KeyFeature
import javax.inject.Inject


class GetKeyFeatureUseCase @Inject constructor(private val keyFeatureRepository: KeyFeatureRepository){

    suspend operator fun invoke(): List<KeyFeature> {
        return keyFeatureRepository.getKeyFeatures()
    }

}