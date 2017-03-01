package to.mattias.constants;

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
