import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  getBadges() {
    return http.get('/badges/');
  },

  getBadgeByName(name) {
    return http.get(`/badges/name/${name}`);
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