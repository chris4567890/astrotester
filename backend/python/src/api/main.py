from flask import Flask,request,jsonify
from ..astrofiles.astroSearches import AstronomySearch

app = Flask(__name__)

@app.route('/search', methods=['GET'])
def searchActivator():
    query = request.args.get('query',type=str)

    if not query:
        return jsonify({'error':'No search provided'}),400
    try:
        result = AstronomySearch.search(query)

        if result is None or len(result) == 0:
            return jsonify({'error':'no result found'}), 404

        result_dict = {
            'columns': result.colnames,
            'data': [list(row) for row in result]

        }
        return jsonify(str(result_dict)) 
    except Exception as e:
        return jsonify({'error':str(e)}),500   


if __name__ =='__main__':
    app.run(host='localhost',port = 5000,debug=True)