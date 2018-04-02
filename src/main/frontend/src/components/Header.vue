<template>
  <div class="header">
    <b-navbar toggleable="md" variant="dark" type="dark" text-variant="white">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand href="#">OPKoKo 2018:2</b-navbar-brand>

      <b-collapse is-nav id="nav_collapse">
        <b-navbar-nav>
          <b-nav-item href="#">Schedule</b-nav-item>
          <b-nav-item href="#">Speakers</b-nav-item>
          <b-nav-item href="#">Workshops</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#">Partners</b-nav-item>
          <b-nav-item href="#">Become a partner</b-nav-item>
          <b-nav-item href="#">Venue+Hotel</b-nav-item>
          <b-nav-item href="#">Register Today</b-nav-item>
        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Header'
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
  background: black;
}

h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
