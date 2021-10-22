import axios from '../custom-axios/axios';
import {articleUrl} from "../util/constants";

const ArticleService = {
    fetchArticles: () => {
        return axios.get(articleUrl);
    },

    deleteArticle: (id) => {
        return axios.delete(articleUrl+`/delete/${id}`);
    },

    addArticle: (title, description, amount, currency, sales) => {
        return axios.post(articleUrl+"/add", null, { params: {
                "title": title,
                "description": description,
                "amount": amount,
                "currency": currency,
                "sales": sales
            }
        });
    },
    editArticle: (id, title, description, amount, currency, sales) => {
        return axios.put(articleUrl+`/edit/${id}`, null, { params: {
                "title": title,
                "description": description,
                "amount": amount,
                "currency": currency,
                "sales": sales
            }
        });
    },
    getArticle: (id) => {
        return axios.get(articleUrl+`/${id}`);
    }

}

export default ArticleService;
