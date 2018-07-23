<template>
  <div class="proposal">
    <b-container>
      <b-row>
        <b-col md="6" offset-md="2">
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="emailGroup"
                          label="Email address:"
                          label-for="email"
                          description="We'll never share your email with anyone else.">
              <b-form-input id="email"
                            type="email"
                            v-model="form.email"
                            required
                            placeholder="Enter email">
              </b-form-input>
            </b-form-group>
            <b-form-group id="titleGroup"
                          label="Title:"
                          label-for="title">
              <b-form-input id="title"
                            type="text"
                            v-model="form.title"
                            required
                            placeholder="Enter title">
              </b-form-input>
            </b-form-group>
            <b-form-group id="descriptionGroup"
                          label="Description:"
                          label-for="description">
              <b-form-textarea id="description"
                            v-model="form.description"
                            required
                            rows="3"
                            max-rows="6"
                            placeholder="Enter a description">
              </b-form-textarea>
            </b-form-group>
            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" variant="secondary">Reset</b-button>
          </b-form>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import {AXIOS} from '../http-common';
export default {
  data: function () {
    return {
      errors: [],
      form: {
        email: '',
        title: '',
        description: '',
        conferenceId: this.$conferenceId
      },
      types: [
        {text: 'Select One', value: null},
        {text: 'Presentation (45 min)', value: 0},
        {text: 'Lightning talk (15 min)', value: 1},
        {text: 'Workshop (180 min)', value: 2}
      ],
      show: true
    };
  },
  methods: {
    onSubmit: function (evt) {
      evt.preventDefault();
      AXIOS.post('proposal', JSON.stringify(this.form))
        .then(response => {})
        .catch(e => {
          this.errors.push(e);
        });
    },
    onReset: function (evt) {
      evt.preventDefault();
      /* Reset our form values */
      this.form.email = '';
      this.form.title = '';
      this.form.description = '';
      this.form.conferenceId = 'c89d6dbb-2c2e-4a53-9fb1-7934575b49d1';
      /* Trick to reset/clear native browser form validation state */
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .proposal {
    text-align: left;
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
