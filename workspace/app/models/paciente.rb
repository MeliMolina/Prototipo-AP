class Paciente < ActiveRecord::Base
    resourcify
    
    validates_format_of :cedula, :with => /([1-9])-([0-9]){4}-([0-9]){4}/, :on => [ :create, :update ],  :message => "Ingresar de manera correcta"
    validates_presence_of :cedula , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :nombre ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :apellido1 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :apellido2 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :fechaNacimiento ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :sexo,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :nacionalidad,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_uniqueness_of :cedula, :on => [ :create, :update ],:message => "ya fue ingresada"
    
    
    has_many :telefonos,  dependent: :destroy 
    has_many :citum, dependent: :destroy
    has_and_belongs_to_many :observacions, dependent: :destroy, :join_table => :observacions_pacientes
    has_and_belongs_to_many :enfermedads, dependent: :destroy, :join_table => :enfermedads_pacientes
    
    has_and_belongs_to_many :medicos, :join_table => :medicos_pacientes
    
    accepts_nested_attributes_for :observacions, reject_if: :all_blank, allow_destroy: true
    accepts_nested_attributes_for :enfermedads, reject_if: :all_blank, allow_destroy: true
    accepts_nested_attributes_for :telefonos, reject_if: :all_blank, allow_destroy: true, limit: 5

    has_attached_file :image, styles: { small: "64x64", med: "100x100", large: "200x200" },
                              default_url: 'icon-user-default.png' 
    validates_attachment :image, :content_type => { :content_type => ["image/jpg","image/png","image/jpeg"],
                                                    :message => ', Solo imágenes jpg, jpeg y png' }
    
    has_many :emails,  dependent: :destroy
    
    accepts_nested_attributes_for :emails, reject_if: :all_blank, allow_destroy: true, limit: 5
    
    def accessible_attributes
     [cedula, nombre, apellido1, apellido2, fechaNacimiento, nacionalidad, genero, fechaFallecimiento]
    end

    def self.search(search)
      if search
        where('cedula LIKE ? OR concat_ws(' ',nombre,apellido1) LIKE ? OR concat_ws(' ',apellido1,apellido2) LIKE ?', "%#{search}%","%#{search}%","%#{search}%")
      else
      end
    end

    MAPPING = {
      "Cedula" => "cedula",
      "Nombre" => "nombre",
      "Primer Apellido" => "apellido1",
      "Segundo Apellido" => "apellido2",
      "Fecha de Nacimiento" => "fechaNacimiento",
      "Nacionalidad" => "nacionalidad",
      "Genero" => "sexo",
      "Fecha de Fallecimiento" => "fechaFallecimiento"
    } 
   
    attr_accessible :cedula, :nombre, :apellido1, :apellido2, :fechaNacimiento, :nacionalidad, :sexo, :fechaFallecimiento
    
    def self.import(file, user)
      spreadsheet = open_spreadsheet(file)
      header = spreadsheet.row(1)
      (2..spreadsheet.last_row).each do |i|
        row = Hash[[header, spreadsheet.row(i)].transpose]
        row.keys.each { |k| row[ MAPPING[k] ] = row.delete(k) if MAPPING[k] }
        nuevo = Paciente.create!(row)
        user.pacientes << nuevo
      end
    end
    
    def self.open_spreadsheet(file)
      case File.extname(file.original_filename)
      when ".csv" then Roo::Csv.new(file.path, packed: nil, file_warning: :ignore)
      when ".xls" then Roo::Excel.new(file.path, packed: nil, file_warning: :ignore)
      when ".xlsx" then Roo::Excelx.new(file.path, packed: nil, file_warning: :ignore)
      else raise "Tipo de Archivo Desconocido: #{file.original_filename}"
      end
    end

end
