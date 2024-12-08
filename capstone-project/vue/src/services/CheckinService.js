import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
});

export default {

  listAttractionById(attractionId) {
    return http.get(`/checkins/attraction/${attractionId}`);
  },

  getCheckinByCheckInId(id) {
    return http.get(`/checkins/${id}`);
  },

  createCheckin(checkin) {
    return http.post('/checkins/', checkin);
  },

  deleteCheckin(id){
    return http.delete(`/checkins/${id}`);
  }

}