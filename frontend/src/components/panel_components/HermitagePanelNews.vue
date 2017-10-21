<template>
  <div id="hermitage_panel_news">
      <div class="newsletter">
          <div class="news">
              <div class="news-headline">
                  <span class="news-headline__text">Test</span>
              </div>
              <div class="news-content">
                  <span class="news-content__text">Test teeeest ts tessts test.</span>
              </div>
              <div class="news-route">
                  <icon name="map-o" scale="1" class="news-route__icon"></icon>
                  <span class="news-route__text">Test route</span>
              </div>
          </div>
          <div class="news">
              <div class="news-headline">
                  <span class="news-headline__text">Test</span>
              </div>
              <div class="news-content">
                  <span class="news-content__text">Test teeeest ts tessts test.</span>
              </div>
              <div class="news-route">
                  <icon name="map-o" scale="1" class="news-route__icon"></icon>
                  <span class="news-route__text">Test route</span>
              </div>
          </div>
      </div>
  </div>
</template>

<script>
    import 'vue-awesome/icons/map-o'
    export default {
      name: 'HermitagePanelNews',
      data () {
        return {
        }
      }
    }
</script>

<style scoped>

    #hermitage_panel_news {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: stretch;
    }

    .newsletter {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        width: 400px;
    }

    .news {
        margin-bottom: 50px;
        height: 200px;
        background: #fff;
        box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
    }

    .news-headline {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        height: 50px;
        background: #3498db;
    }

    .news-headline__text {
        color: #fff;
        font-size: 24px;
        margin-left: 15px;
    }

    .news-content {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        height: 100px;
        border-bottom: 1px solid rgba(0, 0, 0, 0.2);
    }

    .news-content__text {
        margin: 15px;
        font-size: 16px;
        font-family: 'Source Sans Pro', 'Open Sans', 'sans-selif';
    }

    .news-route {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        height: 50px;
        color: #3498db;
    }

    .news-route__icon { margin-left: 15px; }
    .news-route__text { margin-left: 10px; }

</style>
