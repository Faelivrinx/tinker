-- Create the keycloak database if it doesn't exist
CREATE DATABASE keycloak;

-- Create keycloak user
CREATE USER keycloak_user WITH ENCRYPTED PASSWORD 'keycloakpassword';

-- Grant privileges on the keycloak database
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak_user;

-- Connect to keycloak database and set privileges on the public schema
\connect keycloak

-- Ensure keycloak_user can create objects in the public schema
GRANT CREATE, USAGE ON SCHEMA public TO keycloak_user;

-- Optionally, if tables, sequences, or functions are already created, grant all privileges:
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO keycloak_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO keycloak_user;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO keycloak_user;
