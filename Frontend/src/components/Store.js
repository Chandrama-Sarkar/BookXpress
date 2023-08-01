import React from 'react'
import Product from './Store/Product'
import Footer from './Footer'
import Navbar from './Navbar'
import { useParams } from 'react-router-dom'


const Store = () => {

    const userId = useParams();
    return (

        <>
            <Navbar userId={userId} />
            <br />
            <section id="store-page">
                <div class="header-text">
                    Good books are just like <br /><span>good friends</span>.
                </div>

                <div class="products">
                    <Product />
                </div>

                <div class="info">
                    No more results <i class="bi-emoji-smile"></i>
                </div>
            </section>

            <Footer />
        </>
    )
}

export default Store