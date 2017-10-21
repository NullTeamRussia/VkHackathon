<template>
  <div id="hermitage_panel_news">
      <div class="newsletter">
          <div class="news" v-for="(item, index) in news" :key="index">
              <div class="news-headline">
                  <span class="news-headline__text">{{item.title}}</span>
                  <div class="news-headline__actions" @click="deleteNews">
                      <icon
                        :data-content="item"
                        :id="item._links.self.href" 
                        name="trash-o" scale="1.5" 
                        class="headline__action-delete"></icon>
                  </div>
              </div>
              <div class="news-content">
                  <span class="news-content__text">{{item.text}}</span>
              </div>
              <div class="news-route">
                  <icon name="map-o" scale="1" class="news-route__icon"></icon>
                  <span class="news-route__text">Test route</span>
              </div>
          </div>
      </div>
      <div class="news_editor">
          <div class="news-headline">
              <input class="news-headline__text" @keyup.enter="createNews" type="text" v-model="new_article_title" placeholder="Заголовок..."/>
              <div class="news-headline__actions" @click="createNews">
                  <icon name="plus" scale="1.5" class="headline__action-create"></icon>
              </div>
          </div>
          <div class="news-content">
              <textarea class="news-content__text" @ctrl.enter="createNews" v-model="new_article_text" placeholder="Текст..."/>
          </div>
          <div class="news-route">
              <icon name="map-o" scale="1" class="news-route__icon"></icon>
              <span class="news-route__text"></span>
          </div>
      </div>
  </div>
</template>

<script>
    import axios from 'axios'
    import 'vue-awesome/icons/map-o'
    import 'vue-awesome/icons/plus'
    import 'vue-awesome/icons/trash-o'
    export default {
      name: 'HermitagePanelNews',
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
          new_article_title: '',
          new_article_text: ''
        }
      },
      methods: {
        createNews () {
          axios.post('http://api.hermitage.nullteam.info/news', {
            'title': this.new_article_title, 'text': this.new_article_text, 'pathLink': ''
          }).then(resp => {
            this.new_article_text = ''
            this.new_article_title = ''
            this.news.push(resp.data)
            this.$emit('update:news', this.news)
          })
        },
        deleteNews (e) {
          if (e.target.id !== '') {
            axios.delete(e.target.id).then(resp => {
              this.news.splice(this.news.indexOf(e.target.dataset.content), 1)
              this.$emit('update:news', this.news)
            })
          }
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
        min-width: 400px;
        overflow-x: hidden;
        overflow-y: scroll;
    }

    .newsletter::-webkit-scrollbar { width: 0; }

    .news {
        margin-bottom: 50px;
        max-height: 200px;
        background: #fff;
        box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
    }

    .news-headline {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        min-height: 50px;
        background: #3498db;
    }

    .news-headline__text {
        flex-grow: 1;
        color: #fff;
        font-size: 24px;
        margin-left: 15px;
    }

    .news-content {
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        min-height: 100px;
        height: 100%;
        border-bottom: 1px solid rgba(0, 0, 0, 0.2);
    }

    .news-content__text {
        margin: 15px;
        height: 100%;
        font-size: 16px;
        font-family: 'Source Sans Pro', 'Open Sans', 'sans-selif';
        outline: none;
    }

    .news-route {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        min-height: 50px;
        color: #3498db;
    }

    .news-route__icon { margin-left: 15px; }
    .news-route__text { margin-left: 10px; }

    .news_editor {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        flex-grow: 1;
        margin-left: 50px;
        background: #fff;
        box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
    }

    .news_editor .news-headline__text {
        border: none;
        background: #3498db;
        color: #fff;
    }

    .news_editor .news-headline__text::-moz-placeholder          { color: #fff; }
    .news_editor .news-headline__text::-webkit-input-placeholder { color: #fff; }

    .news_editor .news-content__text {
        word-wrap: break-word;
        height: 100%;
        border: none;
    }

    .news-headline__actions {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin-right: 10px;
        color: #fff;
    }

    .news-headline__actions > svg { width: 100%; height: 100%; }
    .news-headline__actions > svg:hover { cursor: pointer }

</style>
