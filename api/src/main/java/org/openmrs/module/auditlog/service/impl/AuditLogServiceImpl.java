package org.openmrs.module.auditlog.service.impl;

import org.openmrs.module.auditlog.contract.AuditLogPayload;
import org.openmrs.module.auditlog.dao.AuditLogDao;
import org.openmrs.module.auditlog.model.AuditLog;
import org.openmrs.module.auditlog.service.AuditLogService;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogDao auditLogDao;

    public ArrayList<SimpleObject> getLogs(String username, String patientId, Date startDateTime, Integer lastAuditLogId, Boolean prev, Boolean defaultView) {
        List<AuditLog> auditLogs = auditLogDao.getLogs(username, patientId, startDateTime, lastAuditLogId, prev, defaultView);
        return (ArrayList<SimpleObject>) (auditLogs.stream().map(AuditLog::map).collect(Collectors.toList()));
    }

    public void createAuditLog(AuditLogPayload log) {
        User user = Context.getAuthenticatedUser();
        Patient patient = Context.getPatientService().getPatientByUuid(log.getPatientUuid());
        AuditLog auditLog = new AuditLog();
        auditLog.setEventType(log.getEventType());
        auditLog.setUser(user);
        auditLog.setEventType(log.getEventType());
        auditLog.setPatient(patient);
        auditLog.setDateCreated(new Date());
        auditLog.setMessage(log.getMessage());
        auditLog.setUuid(UUID.randomUUID().toString());
        auditLogDao.saveAuditLog(auditLog);

    }


}
