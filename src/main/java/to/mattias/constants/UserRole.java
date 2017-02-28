package to.mattias.constants;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by juan on 2017-02-28.
 */
public enum UserRole {

    ADMIN {
        public String toString() {
          return "ADMIN";
        }
    },
    USER {
        public String toString() {
            return "USER";
        }
    },
    CUSTOMER {
        public String toString() {
            return "CUSTOMER";
        }
    }

}
