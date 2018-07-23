<template>
  <div class="mainpanel">
    <b-container fluid class="infopanel">
      <b-row>
        <b-col md="8" offset-md="4">
          <b-jumbotron bg-variant="dark" text-variant="white" border-variant="dark">
            <template slot="header">
              OPKoKo 2018:2
            </template>
            <template slot="lead">
              The second of Omegapoints conferences this year will take place in the beautiful Nässjö. This small town is a very important hub for Swedens rail traffic.
            </template>
            <p>
              The call for proposals are open until 2018-10-04 23:59:59.999
            </p>
            <b-btn variant="primary" text-variant="light" href="#/proposal">Submit a proposal</b-btn>
            <p style="margin-top: 20px">So far {{ proposals }} proposals has been submitted by {{ speakers }} speakers</p>
          </b-jumbotron>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'MainPanel',
  data: function () {
    return {
      proposals: 42,
      speakers: 38
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.mainpanel {
  background-image: url("../assets/mainpanel_bg.png");
  background-color: black;
  height: 960px;
  max-height: 960px;
}
.infopanel {
  padding-top:200px;
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
