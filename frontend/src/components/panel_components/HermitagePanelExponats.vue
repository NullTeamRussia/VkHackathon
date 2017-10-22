<template>
  <div id="hermitage_panel_exponats">
    <div class="exponats_actions">
      <button class="exponats_actions-add">Добавить экспонат</button>
    </div>
    <div class="exponat_container">
      <div class="exponat_card" v-for="(exponat, index) in exponats" :key="index">
        <img :src="exponat.photo" class="card__picture">
        <div class="card__overlay">
          <span class="card__overlay-text">{{exponat.name}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import 'vue-awesome/icons/close'
    export default {
      name: 'HermitagePanelExponats',
      props: [
        'news',
        'is_modal_active',
        'paths',
        'edges',
        'exponats',
        'nodes'
      ],
      data () {
        return {
          modal_active: false
        }
      }
    }
</script>

<style scoped>

  #hermitage_panel_exponats {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: stretch; 
    padding: 50px; 
  }

  .exponat_container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: flex-start;
  }

  .exponat_card {
    max-height: 200px;
    max-width: 200px;
    margin: 0 43px 43px 0;
    background: #fff;
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 1px -1px rgba(0,0,0,.2), 0 1px 2px 0 rgba(0,0,0,.12);
  }

  .card__picture {
    width: 200px;
    height: 200px;
  }

  .card__overlay {
    position: relative;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    bottom: 40px;
    height: 35px;
    background: rgba(0, 0, 0, 0.6);
    cursor: pointer;
  }

  .card__overlay-text {
    color: #fff;
    user-select: none;
  }

  .exponats_actions {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: stretch;
    min-height: 50px;
    margin-bottom: 43px;
    background: #fff;
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 1px -1px rgba(0,0,0,.2), 0 1px 2px 0 rgba(0,0,0,.12);
  }

  .exponats_actions-add {
    padding: 0 16px;
    border: none;
    outline: none;
    background: #fff;
    color: #3498db;
    font-size: 18px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
  }

  .exponats_actions-add:hover {
    color: #fff;
    background: #3498db;
  }

</style>
