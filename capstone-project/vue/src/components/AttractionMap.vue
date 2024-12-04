<!--template>
    <div id="main">

        <gmp-map :center="`${latitude}, ${longitude}`" zoom="10" map-id="DEMO_MAP_ID" style="height: 400px"></gmp-map>


    </div>
</template>

<script>


export default {

    props: ['latitude', 'longitude'],

    data() {
        return {
            test: "Hi!",
            mapcenter2: "37.4220656,-122.0840897",
        }
    },

    computed: {


    },

    mounted() {

        const mapScript = document.createElement('script');

        mapScript.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=maps&v=beta";

        document.body.append(mapScript);

    }

}

</script>

<style scoped></style -->

<script>

export default {

}

</script>