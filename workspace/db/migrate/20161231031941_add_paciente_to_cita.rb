class AddPacienteToCita < ActiveRecord::Migration
  def change
    add_reference :cita, :paciente, index: true, foreign_key: true
  end
end
