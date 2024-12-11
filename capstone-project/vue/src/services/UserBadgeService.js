import axios from 'axios';

const userToken = localStorage.getItem('token');

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
  headers: {Autorization: `Bearer ${userToken}`}
});

export default {

    hasVisitedFiveTimes(userId, attractionType) {
        return http.get(`/ubadges/visits/${userId}/${attractionType}`);
    },

    hasVisitedAllLocations(userId){
        return http.get(`/ubadges/visits/all/${userId}`);
    },

    getBadgeIdByName(name){
        return http.get(`/ubadges/id/${name}`);
    },

    awardBadge(request){
        return http.post(`/ubadges/award`, request);
    }


}