import axios from 'axios';

// api

type Methods = 'get' | 'post' | 'delete' | 'put' | 'patch';

interface apiDataType {
  url: string;
  data?: object;
  params?: object;
  headers?: object;
}

interface apiType extends apiDataType {
  method: Methods;
}

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

const baseHeaders = {
  Accept: '*/*',
  'Content-Type': 'application/json',
};

const sendRequest = ({ url, params, method, headers }: apiType) => {
  return axios[method](BASE_URL + url, {
    headers: { ...baseHeaders, ...headers },
    params,
  }).then((response) => {
    return { ...response.data, axiosStatus: response.status };
  });
};

const sendRequestWithData = ({ url, data, method, headers }: apiType) => {
  return axios[method](BASE_URL + url, data, {
    headers: { ...baseHeaders, ...headers },
  }).then((response) => {
    return response.data;
  });
};

export const api = {
  get: ({ url, params, headers }: apiDataType) =>
    sendRequest({ url, params, method: 'get', headers }),
  post: ({ url, data, headers }: apiDataType) =>
    sendRequestWithData({ url, data, method: 'post', headers }),
  put: ({ url, data, headers }: apiDataType) =>
    sendRequestWithData({ url, data, method: 'put', headers }),
  patch: ({ url, data, headers }: apiDataType) =>
    sendRequestWithData({ url, data, method: 'patch', headers }),
};

////////////////////////////

export const postRequestPage = async (body: object, token: string | null) => {
  const { data } = await api.post({
    url: `http://15.164.36.225/api/deal-boards/write`,
    data: body,
    headers: { 'Content-Type': 'application/json', Authorization: token },
  });
  return data;
};
