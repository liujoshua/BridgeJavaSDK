package org.sagebionetworks.bridge.sdk;

/**
 * Created by liujoshua on 10/4/16.
 */
public interface ClientFactory {

    DeveloperClient getDeveloperClient();

    ResearcherClient getResearcherClient();

    WorkerClient getWorkerClient();

    UserClient getUserClient();

    AdminClient getAdminClient();

    ExternalIdentifiersClient getExternalIdentifiersClient();

    ParticipantClient getParticipantClient();

    SchedulePlanClient getSchedulePlanClient();

    StudyClient getStudyClient();

    StudyConsentClient getStudyConsentClient();

    SubpopulationClient getSubpopulationClient();

    SurveyClient getSurveyClient();

    UploadSchemaClient getUploadSchemaClient();

    ReportClient getReportClient();
}
