import axios from '../custom-axios/axios';

const ArticleService = {
    fetchArticles: () => {
        return axios.get("/article");
    },
    fetchOrders: () => {
        return axios.get("/orders")
    }


}

export default ArticleService;
