import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
});

export default {

    hasVisitedFiveTimes(userId, attractionType) {
        return http.get(`/ubadges/visits/${userId}/${attractionType}`);
    },

    getBadgeIdByName(name){
        return http.get(`/ubadges/id/${name}`);
    },

    awardBadge(request){
        return http.post(`/ubadges/award`, request);
    }


}