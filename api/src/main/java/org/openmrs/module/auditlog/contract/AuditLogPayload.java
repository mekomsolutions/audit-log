package org.openmrs.module.auditlog.contract;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditLogPayload {

    private String patientUuid;
    private String eventType;
    private String message;
    private String module;

    public AuditLogPayload(){
        super();
    }

    public AuditLogPayload(String patientUuid, String message, String eventType, String module) {
        this.patientUuid = patientUuid;
        this.eventType = eventType;
        this.message = message;
        this.module = module;
    }

    public String getPatientUuid() {
        return patientUuid;
    }

    public String getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }
    
    public String getModule() {
        return module;
    }
}
