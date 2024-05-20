import {Environment} from "./ienvironment";

const host = "http://localhost:8037"

export const environment: Environment = {
  production: false,
  apiHost: host,
  apiUrl: `${host}/api/`,
  login: `${host}/api/login`
}
