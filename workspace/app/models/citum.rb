class Citum < ActiveRecord::Base
    #validaciones para cita
  validates_presence_of :fecha , :on => [ :create, :update ],:message => "no puede estar vacío"
  validates_uniqueness_of :fecha, :on => [ :create, :update ],:message => "ya fue ingresada"
  validates_presence_of :hora ,:on => [ :create, :update ], :message => "no puede estar vacío"
  
  belongs_to :medico
  belongs_to :paciente
  has_and_belongs_to_many :observacions, dependent: :destroy, :join_table => :observacions_citas
  accepts_nested_attributes_for :observacions, reject_if: :all_blank, allow_destroy: true
  

end
