/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package connectors

import config.MicroserviceAppConfig
import javax.inject.Inject
import models.{SubmissionArchiveRequest, SubmissionArchiveResponse}
import play.api.Logger
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.play.bootstrap.http.HttpClient

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class CasConnector @Inject()(appConfig: MicroserviceAppConfig, http: HttpClient)(implicit hc: HeaderCarrier, ec: ExecutionContext) {

  def archiveSubmission(submissionRef: String, data: SubmissionArchiveRequest): Future[SubmissionArchiveResponse] = {
    Logger.debug(s"Sending submission $submissionRef to CAS via DMS API")

    val url: String = s"${appConfig.dmsApiUrl}/digital-form/archive/$submissionRef"
    http.POST[SubmissionArchiveRequest, SubmissionArchiveResponse](url, data)
  }
}
