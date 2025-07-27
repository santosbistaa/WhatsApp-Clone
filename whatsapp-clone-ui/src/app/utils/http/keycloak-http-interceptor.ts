import {HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {KeycloakService} from '../keycloak/keycloak.service';
import {inject} from '@angular/core';


// This automatically attach a JWT token from keycloak to every outgoing http request,
// Token is sent in headers, backend can authorize requests, secured


export const keycloakHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = inject(KeycloakService);
  const token = keycloakService.keycloak.token;
  if (token) {
    const authReq = req.clone ( {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      })
    });
    return next(authReq);
  }
  return next(req);
};
