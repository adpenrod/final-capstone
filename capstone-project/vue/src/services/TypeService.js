import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  getTypes() {
    return http.get('/types/');
  },

  getTypeById(id) {
    return http.get(`/types/${id}`);
  },

  getTypeByName(name){
    return http.get(`/types/name/${name}`);
  },

  update(type){
    return http.put(`/types/${type.id}`, type);
  },

  createType(type){
    return http.post('/types/', type);
  },

  delete(id){
    return http.delete(`/types/${id}`);
  }
} 