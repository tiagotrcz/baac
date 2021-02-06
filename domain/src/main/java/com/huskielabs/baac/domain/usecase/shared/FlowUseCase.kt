package com.huskielabs.baac.domain.usecase.shared

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in Params, out Result> {
  operator fun invoke(params: Params): Flow<Result>
}