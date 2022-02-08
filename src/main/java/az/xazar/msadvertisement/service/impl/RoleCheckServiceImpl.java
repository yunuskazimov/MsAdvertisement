package az.xazar.msadvertisement.service.impl;

import az.xazar.msadvertisement.client.PermissionClientRest;
import az.xazar.msadvertisement.service.RoleCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "permissionService")
@Slf4j
@RequiredArgsConstructor
public class RoleCheckServiceImpl implements RoleCheckService {
    private final PermissionClientRest permissionClient;

    @Override
    public boolean checkRole(Long userId, String userRole) {
        log.info("checkRole start with user Id: {}, Role: {}", userId, userRole);
        return permissionClient.getRoleByUserId(userId, userRole);
    }
}
