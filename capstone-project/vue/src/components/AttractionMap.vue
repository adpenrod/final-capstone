<template>
    <div class="map-container">
        <h2>Directions to Location</h2>
        <div ref="map" class="map"></div>
    </div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";
import AttractionsService from "../services/AttractionsService.js";


export default {

    props: [],

    data() {
        return {
            attractions: [],
            userLocation: null,
            map: null,
        }
    },

    computed: {


    },

    mounted() {

        this.createMap();
        this.addUserLocation();

    },

    methods: {

        createMap() {
            const loader = new Loader({
                apiKey: "AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg",
                libraries: ["places"],
            });

            return loader.load().then(() => {
                this.map = new google.maps.Map(this.$refs.map, {
                    center: { lat: 39.9526, lng: -75.1652 },
                    zoom: 12,
                });
            });

        },

        addUserLocation() {

            navigator.geolocation.getCurrentPosition(

                (position) => {
                    this.userLocation = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                    }


                    new google.maps.Marker({
                        position: this.userLocation,
                        map: this.map,
                        title: "You are here",
                        icon: {
                            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
                        },
                    });

                    this.map.setCenter(this.userLocation);
                },
            );

        },
    }

}

</script>

<style scoped>
.map-container {
    width: 100%;
    height: 100%;
    display: flex;
}

.map {
    width: 100%;
    height: 500px;
}
</style>

