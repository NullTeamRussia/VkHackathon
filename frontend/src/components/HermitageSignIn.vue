<template>
    <div id="hermitage_sign_in">
        <form class="sign_in__form">
            <div class="form-field">
                <input class="form-field__input" id="sign_in__form-login" v-model.trim="login" type="text" placeholder="Логин">
                <icon name="user" scale="2" class="form-field__icon--user"></icon>
            </div>
            <div class="form-field">
                <input class="form-field__input" id="sign_in__form-password" v-model.trim="password" type="password" placeholder="Пароль">
                <icon name="lock" scale="2" class="form-field__icon--lock"></icon>
            </div>
            <div class="form-field__actions">
                <input
                @click.prevent="authorize" 
                class="form-field__submit" 
                type="submit" 
                value="Войти"/>
            </div>
        </form>
    </div>
</template>

<script>
import 'vue-awesome/icons/user'
import 'vue-awesome/icons/lock'
export default {
  name: 'HermitageSignIn',
  data () {
    return {
      login: '',
      password: ''
    }
  },
  methods: {
    authorize () {
      if (this.login === 'admin' && this.password === 'qazwsx123') {
        this.$router.push({name: 'hermitage_panel_main'})
      }
    }
  }
}
</script>

<style scoped>

    #hermitage_sign_in {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        width: 100vw;
        background-color: #fff;
    }

    .sign_in__form {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
        height: 304px;
        width: 598px;
        background-color: #ecf0f1;
        border-radius: 7px;
        box-shadow: 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 1px -1px rgba(0,0,0,.2), 0 1px 2px 0 rgba(0,0,0,.12);
    }

    .form-field {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        width: 534px;
        height: 55px;
        border-radius: 7px;
        background-color: #ffffff;
    }

    .form-field .fa-icon { color: #95a5a6; }

    .form-field .form-field__icon--user {
        width: 21px;
        height: 26px;
    }

    .form-field .form-field__icon--lock {
        margin-top: 4px;
        width: 21px;
        height: 26px;
    }

    .form-field__input {
        width: 474px;
        height: 52px;
        font-size: 24px;
        color: #95a5a6;
        background-color: #fff;
        border: none;
        outline: none;
    }

    .form-field__input::-webkit-input-placeholder { color: #95a5a6; }
    .form-field__input:-moz-placeholder { color: #95a5a6; }
    .form-field__input::-moz-placeholder { color: #95a5a6; }
    .form-field__input:-ms-input-placeholder { color: #95a5a6; }

    .form-field__submit {
        width: 100%;
        height: 55px;
        border-radius: 7px;
        border: none;
        background-color: #3498db;
        text-transform: uppercase;
        color: #fff;
        font-size: 30px;
        outline: none;
        cursor: pointer;
        transition: all 0.3s ease-in-out;
    }

    .form-field__submit:hover {  background: #2980b9; }

    .form-field__actions {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        width: 534px;
    }

</style>