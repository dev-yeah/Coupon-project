<template>
  <b-navbar toggleable="md" type="dark" variant="danger">
  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
  <b-navbar-brand href="#">{{ title }}</b-navbar-brand>
  <b-collapse is-nav id="nav_collapse">
  </b-collapse>
</b-navbar>

<!-- navbar-1.vue -->
</template>

<script>
export default {
  name: 'coupon-header',
  data () {
    return {
      title: 'Heeye`s Coupon Gift'
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset (evt) {
      evt.preventDefault()
      /* Reset our form values */
      this.form.email = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
