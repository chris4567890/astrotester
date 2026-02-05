export default function searchField(){
    return(
        <>
            <div id="searchField">
                <form onSubmit={handleSubmit}>
                    <input type="text" />
                    <br/>
                    <button type="submit">search</button>
                </form>
            </div>
        </>
    )
}