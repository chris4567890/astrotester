import React from 'react';
import { BrowserRouter, Routes } from 'react-router-dom'
import SearchField from './components/SearchField'

function App() {
    return(
        <>
            <div>
                <BrowserRouter>
                    <SearchField />
                    <Routes>
                    </Routes>
                </BrowserRouter>
            </div>
        </>
    )
}

export default App