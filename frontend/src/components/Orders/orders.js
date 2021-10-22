import React from "react";

const orders = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <h4 className={"text-center"}>Orders</h4>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Ordered ON</th>
                            <th scope={"col"}>Order state</th>
                            <th scope={"col"}>Order currency</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default orders;