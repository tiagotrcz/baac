package com.huskielabs.baac.domain.usecase.shared

interface UseCase<in Params, out Result> {
  suspend operator fun invoke(params: Params): Result
}