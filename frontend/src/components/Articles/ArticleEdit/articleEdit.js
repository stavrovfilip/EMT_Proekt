import React from 'react';
import {useHistory} from 'react-router-dom';

const ArticleEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        title: "",
        description: "",
        amount: 0,
        currency: 0,
        sales: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();

        const title = formData.title !== "" ? formData.title : props.article.title;
        const description = formData.description !== "" ? formData.description : props.article.description;
        const amount = formData.amount !== 0 ? formData.amount : props.article.amount;
        const currency = formData.currency !== 0 ? formData.currency : props.article.currency;
        const sales = formData.sales !== 0 ? formData.sales : props.article.sales;

        props.onEditArticle(props.article.id.id, title, description, amount, currency, sales);
        history.push("/articles");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <br/>
                        <label htmlFor="title">Article title</label>
                        <input type="text"
                               className="form-control"
                               id="title"
                               name="title"
                               required
                               placeholder={props.article.title}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               placeholder={props.article.description}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="amount">Amount</label>
                        <input type="text"
                               className="form-control"
                               id="amount"
                               name="amount"
                               placeholder={props.article.amount}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Currency</label>
                        <select name="currency" className="form-control" onChange={handleChange}>
                            <option value="EUR">EUR</option>
                            <option value="USD">USD</option>
                            <option value="MKD">MKD</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="sales">Sales</label>
                        <input type="text"
                               className="form-control"
                               id="sales"
                               name="sales"
                               placeholder={props.article.sales}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <button id="submit" type="submit" className="btn btn-danger">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default ArticleEdit;
