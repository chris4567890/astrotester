import { useState } from "react";

export default function SearchField() {
    const [query,setQuery] = useState('');
    const [results, setResults] = useState(null);
    const [error,setError] = useState(null);
    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        
        try{
            const response = await fetch(`http://localhost:7070/search?q=${encodeURIComponent(query)}`);
            
            if(!response.ok){
                throw new Error(`Error:${response.status}`);
            }
            const data = await response.json();
            setResults(data);
            console.log('search result',data);
        }catch(err){
            setError(err.message);
            console.log('error fetching data:',err)
        }
    };

    return(
        <>
            <div id="searchField">
                <form onSubmit={handleSubmit}>
                    <input 
                        type="text" 
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                        placeholder="enter object name"
                    />
                    <br/>
                    <button type="submit">search</button>
                </form>
            </div>
        </>
    )
}