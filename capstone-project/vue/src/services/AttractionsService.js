import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  listAttractions() {
    return http.get('/attractions/')
  },

  getAttractionId(id) {
    return http.get(`/attractions/${id}`)
  },

  getAttractionByName(userInput){
    return http.get(`/attractions/name/${userInput}`);
  },

  getAttractionByAddress(userInput){
    return http.get(`/attractions/address/${userInput}`);
  },

  getAttractionByLatitude(id){
    return http.get(`/attractions/latitude/${id}`);
  },

  getAttractionByLongitude(id){
    return http.get(`/attractions/longitude/${id}`);
  },

  updateAttraction(attraction){
    return http.put(`/attractions/${attraction.id}`, attraction);
  },

  createAttraction(attraction){
    return http.post(`/attractions/`, attraction);
  },

  deleteAttraction(id){
    return http.delete(`/attractions/${id}`);
  }

}