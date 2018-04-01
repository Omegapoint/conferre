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
                            type="text"
                            v-model="form.description"
                            required
                            rows="3"
                            max-rows="6"
                            placeholder="Enter description">
              </b-form-textarea>
            </b-form-group>
            <b-form-group id="typeGroup"
                          label="Type:"
                          label-for="type">
              <b-form-select id="exampleInput3"
                             :options="types"
                             required
                             v-model="form.type">
              </b-form-select>
            </b-form-group>
            <b-form-group id="exampleGroup4">
              <b-form-checkbox-group v-model="form.checked" id="exampleChecks">
                <b-form-checkbox value="me">Check me out</b-form-checkbox>
                <b-form-checkbox value="that">Check that out</b-form-checkbox>
              </b-form-checkbox-group>
            </b-form-group>
            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
          </b-form>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          email: '',
          title: '',
          description: '',
          type: null,
          checked: []
        },
        types: [
          {text: 'Select One', value: null},
          'Presentation (45 min)', 'Lightning talk (15 min)', 'Workshop (180 min)'
        ],
        show: true
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        /* Reset our form values */
        this.form.email = ''
        this.form.title = ''
        this.form.description = ''
        this.form.type = null
        this.form.checked = []
        /* Trick to reset/clear native browser form validation state */
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
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
