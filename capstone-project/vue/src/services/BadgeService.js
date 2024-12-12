import axios from 'axios';

const userToken = localStorage.getItem('token');

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
  headers: {Authorization: `Bearer ${userToken}`}

});

export default {

  getBadges() {
    return http.get('/badges/');
  },

  getBadgeByName(name) {
    return http.get(`/badges/name/${name}`);
  },

  getBadgeById(id) {
    return http.get(`/badges/${id}`);
  },

  getBadgeByTypeId(typeId) {
    return http.get(`/badge/type/${typeId}`)
  },

  update(badge) {
    return http.put(`/badges/${badge.id}`, badge);
  },

  createBadge(badge) {
    return http.post('/badges/', badge);
  },

  delete(id) {
    return http.delete(`/badges/${id}`);
  },
}