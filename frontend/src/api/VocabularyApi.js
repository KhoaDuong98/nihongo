import axiosClient from "./axiosClient";

const vocabularyApi = {
    getAll(params) {
        const url = '/vocabularies';
        return axiosClient.get(url, { params });
    },

    get(id) {
        const url = `/vocabularies/${id}`;
        return axiosClient.get(url);
    },
    add(data) {
        const url = '/vocabularies';
        return axiosClient.post(url, data);
    },

    update(data) {
        const url = `/vocabularies/${data.id}`;
        return axiosClient.patch(url, data);
    },
    remove(id) {
        const url = `/vocabularies/${id}`;
        return axiosClient.delete(url);
    }
};
export default vocabularyApi;