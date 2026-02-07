from astroquery.simbad import Simbad
from astroquery.skyview import SkyView
class AstronomySearch:
    def __init__(self,name):
        self.name = name
    @staticmethod
    def search(name):
        result = Simbad.query_object(name)
        return result
    